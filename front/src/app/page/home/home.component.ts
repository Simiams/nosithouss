import {Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import {logMessages} from "@angular-devkit/build-angular/src/builders/browser-esbuild/esbuild";
import { FavoriteService } from 'src/app/_service/favorite.service';
import { PostService } from 'src/app/_service/post.service';
import { now } from 'src/app/_utils/utils';
import { IPostReq, IPostRes } from 'src/app/_interfaces/post';

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

export class HomeComponent implements OnInit{
  posts: IPostRes[] = [];
  assetsBaseUrl = "http://localhost:8080/api/assets/" //todo global
  currentPost: IPostReq = {
    number: 25,
    createdAt: now(),
  }

  // images = [
  //   "nature",
  //   "sky",
  //   "grass",
  //   "mountains",
  //   "rivers",
  //   "glacier",
  //   "forest",
  //   "streams",
  //   "rain",
  //   "clouds",
  // ];

  constructor(private favoriteService: FavoriteService, private router: Router, private postService: PostService) {
    // this.posts = this.images.map((image, index) => ({
    //   id: `post_${index}`,
    //   title: `Card ${index + 1}`,
    //   description: `Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. Separated they live in Bookmarksgrove right at the coast of the Semantics, a large language ocean.`,
    //   imageUrl: `https://source.unsplash.com/random/500X500?${image}`,
    //   liked: this.favoriteService.isPostLiked(`post_${index}`)
    // }));
  }

  ngOnInit() {
    this.getPosts(this.currentPost)
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
      data => this.posts = data,
      error => console.log(error)
    )
  }
}
