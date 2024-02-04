import { Injectable } from '@angular/core';
import { Post } from '../profile/profile.component'; 

@Injectable({
  providedIn: 'root'
})
export class FavoriteService {
  favorites: Post[] = [];
  private localStorageKey = 'favorites';

  constructor() { 
    this.loadFavorites();
  }

  addFavorite(post: Post) {
    this.favorites.push(post);
    this.saveFavorites();
  }

  getFavorites() {
    return this.favorites;
  }

  saveFavorites() {
    localStorage.setItem(this.localStorageKey, JSON.stringify(this.favorites));
  }

  isPostLiked(postId: string): boolean {
    return this.favorites.some(post => post.id === postId);
  }

  private loadFavorites() {
    const favoritesStr = localStorage.getItem(this.localStorageKey);
    if (favoritesStr) {
      this.favorites = JSON.parse(favoritesStr);
    }
  }
}

