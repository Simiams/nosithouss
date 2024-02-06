import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import { FavoriteService } from '../_service/favorite.service';
import {IPostRes} from "../_interfaces/post";
import {UserService} from "../_service/user.service";
import {defaultIProfileGet, IProfileGet} from "../_interfaces/user";

// export interface Post {
//   title: string;
//   description: string;
//   imageUrl: string;
//   liked: boolean;
//   id: string; // Ajoutez un identifiant unique pour chaque post
// }

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
})

export class ProfileComponent implements OnInit{
  assetsBaseUrl = "http://localhost:8080/api/assets/" //todo global
  favorites: IPostRes[] = [];
  posts: IPostRes[] = [];
  currentProfile: IProfileGet = defaultIProfileGet;

  currentImage: string | ArrayBuffer | null = 'https://via.placeholder.com/150';

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

  constructor(private favoriteService: FavoriteService, private route: ActivatedRoute, private userService: UserService) {
    // for (let i = 0; i < 10; i++) {
    //   const postId = `post_${i}`;
    //   this.posts.push({
    //     id: postId,
    //     title: `Post ${i + 1}`,
    //     description: `Description du post ${i + 1}`,
    //     imageUrl: `https://source.unsplash.com/random/500X500?${this.images[i]}`,
    //     liked: false
    //  });
    // }
    this.favorites = this.favoriteService.getFavorites();
  }

  ngOnInit(): void {
    this.getCurrentProfile();
  }


  getCurrentProfile() {
    this.userService.getCurrentProfile().subscribe(
      data => this.currentProfile = data,
      err => console.error(err)
    )
  }


  onFileSelected(event: any) {
    const file = event.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = () => {
        this.currentImage = reader.result;
      };
    }
  }

  toggleLike(post: IPostRes) {
    const index = this.favorites.findIndex(p => p.id === post.id);
    if (index !== -1) {
      this.favorites.splice(index, 1);
      this.favoriteService.saveFavorites();
    }
  }

}
