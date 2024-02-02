import { Component } from '@angular/core';

export interface Post {
  title: string;
  description: string;
  imageUrl: string;
}

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
})



export class ProfileComponent {
  currentImage: string | ArrayBuffer | null = 'https://via.placeholder.com/150'; // Image de profil par défaut

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
  constructor() {
    // Générer des faux posts
    for (let i = 0; i < 10; i++) {
      this.posts.push({
        title: `Post ${i + 1}`,
        description: `Description du post ${i + 1}`,
        imageUrl: `https://source.unsplash.com/random/500X500?${this.images[i]}`,
      });
    }
  }
}
