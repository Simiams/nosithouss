import { Component } from '@angular/core';

@Component({
  selector: 'app-new-post',
  templateUrl: './new-post.component.html',
  styleUrls: ['./new-post.component.css']
})
export class NewPostComponent {
  loadFile = (event: Event) => {
    const input = event.target as HTMLInputElement;
    const image = document.getElementById('output') as HTMLImageElement;
    
    if (input.files && input.files[0]) {
        image.src = URL.createObjectURL(input.files[0]);
    }
  };
};


