import { Component } from '@angular/core';

type CardContent = {
  title: string;
  description: string;
  imageUrl: string;
};

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})

export class HomeComponent {
  cards: CardContent[] = [];
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
      const cards: CardContent[] = [];
      for (let i = 0; i < this.images.length; i++) {
        cards.push({
          title: `Card ${i + 1}`,
          description: `Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. Separated they live in Bookmarksgrove right at the coast of the Semantics, a large language ocean. `,
          imageUrl: `https://source.unsplash.com/random/500X500?${this.images[i]}`,
        });
      }

      this.cards = cards;
    }
  
}
