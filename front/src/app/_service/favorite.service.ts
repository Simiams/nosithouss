import {Injectable} from '@angular/core';
import {IPostRes} from "../_interfaces/post";

@Injectable({
  providedIn: 'root'
})
export class FavoriteService {
  favorites: IPostRes[] = [];
  private localStorageKey = 'favorites';

  constructor() {
    this.loadFavorites();
  }

  addFavorite(post: IPostRes) {
    this.favorites.push(post);
    this.saveFavorites();
  }

  getFavorites() {
    return this.favorites;
  }

  saveFavorites() {
    localStorage.setItem(this.localStorageKey, JSON.stringify(this.favorites));
  }

  isPostLiked(postId: number): boolean {
    return this.favorites.some(post => post.id === postId);
  }

  private loadFavorites() {
    const favoritesStr = localStorage.getItem(this.localStorageKey);
    if (favoritesStr) {
      this.favorites = JSON.parse(favoritesStr);
    }
  }
}

