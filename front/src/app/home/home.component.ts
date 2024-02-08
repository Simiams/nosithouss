import {Component, OnInit, Renderer2} from '@angular/core';
import {Router} from '@angular/router';
import {FavoriteService} from '../_service/favorite.service';
import {IPostReq, IPostRes} from "../_interfaces/post";
import {PostService} from "../_service/post.service";
import {now} from "../_utils/utils";

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
  styleUrls: ['./home.component.css']
})

export class HomeComponent implements OnInit {
  posts: IPostRes[] = [];
  assetsBaseUrl = "http://localhost:8080/api/assets/" //todo global
  currentPost: IPostReq = {
    number: 25,
    createdAt: now(),
  }

  constructor(private favoriteService: FavoriteService, private router: Router, private postService: PostService, private renderer: Renderer2) {
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
}
