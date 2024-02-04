import {Component} from '@angular/core';
import { Router } from '@angular/router';
import { FavoriteService } from '../_service/favorite.service';

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

export class HomeComponent {
  posts: Post[] = [];
  images = [
    "nature",
    "sky",
    "grass",
    "mountains",
    "rivers",
    "glacier",
    "forest",
    "streams",
    "rain",
    "clouds",
  ];

  constructor(private favoriteService: FavoriteService, private router: Router) {
    this.posts = this.images.map((image, index) => ({
      id: `post_${index}`,
      title: `Card ${index + 1}`,
      description: `Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. Separated they live in Bookmarksgrove right at the coast of the Semantics, a large language ocean.`,
      imageUrl: `https://source.unsplash.com/random/500X500?${image}`,
      liked: this.favoriteService.isPostLiked(`post_${index}`)
    }));
  }

  likePost(post: Post) {
    post.liked = !post.liked;
    this.favoriteService.addFavorite(post);
  }

  toggleLike(post: Post) {
    post.liked = !post.liked;
  }
}
