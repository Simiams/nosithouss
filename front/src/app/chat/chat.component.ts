import {Component} from "@angular/core";
import {MatButtonModule} from "@angular/material/button";
import {MatIconModule} from "@angular/material/icon";

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  standalone: true,
  imports: [
    MatButtonModule,
    MatIconModule
  ],
  styleUrls: ['./chat.component.css']
})

export class ChatComponent {
  test: string = "TEST"
}
