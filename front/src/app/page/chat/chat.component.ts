import {AfterViewInit, Component, ElementRef, OnInit, ViewChild} from "@angular/core";
import {MatButtonModule} from "@angular/material/button";
import {MatIconModule} from "@angular/material/icon";
import {NgClass, NgForOf, NgIf, NgOptimizedImage} from "@angular/common";
import {FormsModule} from "@angular/forms";
import {ActivatedRoute} from "@angular/router";
import { ChatService } from "src/app/_service/chat.service";
import { IMessageRes } from "src/app/_interfaces/chat/message";
import { printTimestamp } from "src/app/_utils/utils";

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  standalone: true,
  imports: [
    MatButtonModule,
    MatIconModule,
    NgForOf,
    FormsModule,
    NgClass,
    NgIf,
    NgOptimizedImage
  ],
  styleUrls: ['./chat.component.css']
})

export class ChatComponent implements OnInit, AfterViewInit {
  @ViewChild('scrollContainer') private scrollContainer!: ElementRef;
  private chatService: ChatService;
  user: string = "testest1@."
  messages: IMessageRes[] = []
  messageContent: string = "";

  constructor(chatService: ChatService,private route: ActivatedRoute) {
    this.chatService = chatService
  }

  ngOnInit(): void {
    this.user = this.route.snapshot.params['username'];
    this.getMessages()
  }

  ngAfterViewInit() {
    this.observeChildChanges();
  }

  private scrollToBottom(): void {
    try {
      const element = this.scrollContainer.nativeElement;
      element.scrollTop = element.scrollHeight;
    } catch (err) {
      console.error(err);
    }
  }

  getMessages() {
    this.chatService.getMessages(this.user).subscribe(
      data => this.messages = data.map(d => ({...d, createdAt: printTimestamp(d.createdAt)})),
      err => console.error(err)
    )
  }

  private observeChildChanges(): void {
    const observer = new MutationObserver((mutations) => {
      mutations.forEach((mutation) => {
        if (mutation.type === 'childList') {
          this.scrollToBottom();
        }
      });
    });
    const config = {childList: true};
    observer.observe(this.scrollContainer.nativeElement, config);
  }

  sendMessage() {
    this.chatService.sendMessage({userIdentifier: this.user, content: this.messageContent}).subscribe(
      data => this.getMessages(),
      err => console.error(err)
    )
    this.messageContent = ""
  }
}
