import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {IPostRes} from 'src/app/_interfaces/post';
import {defaultIProfileGet, IProfileGet} from 'src/app/_interfaces/user';
import {FavoriteService} from 'src/app/_service/favorite.service';
import {UserService} from 'src/app/_service/user.service';
import {printTimestamp} from "../../_utils/utils";
import {environment} from "../../../environments/environments";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
})

export class ProfileComponent implements OnInit {
  assetsBaseUrl = environment.assetsBaseUrl;
  favorites: IPostRes[] = [];
  posts: IPostRes[] = [];
  postsGuarding: IPostRes[] = [];
  currentProfile: IProfileGet = defaultIProfileGet;

  // currentImage: string | ArrayBuffer | null = 'https://via.placeholder.com/150';

  constructor(private favoriteService: FavoriteService, private route: ActivatedRoute, private userService: UserService) {
    this.favorites = this.favoriteService.getFavorites();
  }

  ngOnInit(): void {
    if (this.route.snapshot.params['username']) {
      this.getProfile()
      this.getPostByUsername();
    } else {
      this.getCurrentProfile();
      this.getOwnPosts()
    }
    this.getPostGuarding();
  }


  getCurrentProfile() {
    this.userService.getCurrentProfile().subscribe(
      data => this.currentProfile = data,
      err => console.error(err)
    )
  }

  getProfile() {
    this.userService.getProfile(this.route.snapshot.params['username']).subscribe(
      data => this.currentProfile = data,
      err => console.error(err)
    )
  }

  toggleLike(post: IPostRes) {
    const index = this.favorites.findIndex(p => p.id === post.id);
    if (index !== -1) {
      this.favorites.splice(index, 1);
      this.favoriteService.saveFavorites();
    }
  }

  private getOwnPosts() {
    this.userService.getOwnPosts().subscribe(
      data => this.posts = data,
      err => console.error(err)
    )
  }


  private getPostByUsername() {
    console.log(this.route.snapshot.params['username'])
    this.userService.getPostByUsername(this.route.snapshot.params['username']).subscribe(
      data => this.posts = data,
      err => console.error(err)
    )
  }

  protected readonly printTimestamp = printTimestamp;

  private getPostGuarding() {
    this.userService.getPostsGuarding().subscribe(
      data => this.postsGuarding = data,
      err => console.error(err)
    )
  }
}
