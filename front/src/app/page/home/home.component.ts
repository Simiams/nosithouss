import {Component, OnInit, Renderer2} from '@angular/core';
import {Router} from '@angular/router';
import {FavoriteService} from 'src/app/_service/favorite.service';
import {PostService} from 'src/app/_service/post.service';
import {now, printTimestamp} from 'src/app/_utils/utils';
import {IPostReq, IPostRes} from 'src/app/_interfaces/post';
import {EPostType} from "../../_interfaces/ennums";
import {ChatService} from "../../_service/chat.service";

type Post = {
  id: string;
  title: string;
  description: string;
  imageUrl: string;
  liked: boolean;
};

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})

export class HomeComponent implements OnInit {
  posts: IPostRes[] = [];
  assetsBaseUrl = "http://localhost:8080/api/assets/" //todo global
  currentPost: IPostReq = {
    number: 25,
    createdAt: now(),
  }

  constructor(private favoriteService: FavoriteService, private router: Router, private postService: PostService, private renderer: Renderer2, private messageService: ChatService) {
  }

  ngOnInit() {
    this.getPosts(this.currentPost)
  }

  getMore() {
    this.getPosts({
      number: 5,
      createdAt: this.posts[this.posts.length - 1].createdAt.toString(),
    })
  }

  likePost(post: IPostRes) {
    post.liked = !post.liked;
    this.favoriteService.addFavorite(post);
  }

  toggleLike(post: Post) {
    post.liked = !post.liked;
  }


  getPosts(post: IPostReq) {
    this.postService.getPosts(post).subscribe(
      data => this.posts.push(...data),
      error => console.log(error)
    )
  }

  protected readonly printTimestamp = printTimestamp;
  protected readonly EPostType = EPostType;

  redirectUser(userName: string) {
    this.router.navigate(["/profile", userName])
  }

  redirectMessage(authorUserName: string, postId: number) {
    this.messageService.sendGuardRequest({
      content: "",
      type: "GUARD_CLAIM",
      accept: null,
      postId: postId
    }, authorUserName).subscribe(
      response => this.router.navigate(["/chat", authorUserName]),
      error => console.error('Erreur de sendGuardRequest:', error)
    );
  }
}
