import { Component } from '@angular/core';

@Component({
  selector: 'app-accueil',
  templateUrl: './accueil.component.html',
  styleUrls: ['./accueil.component.css']
})
export class AccueilComponent {
  cards = Array.from({ length: 9 }, (_, index) => index + 1); // Example array, adjust as needed

}
