import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import { IContactRes } from 'src/app/_interfaces/chat/contact';
import { ContactService } from 'src/app/_service/contact.service';
import { printTimestamp } from 'src/app/_utils/utils';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit {
  contacts: IContactRes[] = [];
  green: string = "rgba(78,94,69,0.46)";
  private contactService: ContactService
  router!: Router;

  constructor(contactService: ContactService, router: Router) {
    this.contactService = contactService;
    this.router = router
  }

  ngOnInit(): void {
    this.getContacts();
  }

  getContacts() {
    this.contactService.getContacts().subscribe(
      data => this.contacts = data.map(d => ({
        userName: d.userName,
        lastChat: printTimestamp(d.lastChat)
      })),
    err => console.error(err)
    )
  }

  redirectToChat(userName: string) {
    this.router.navigate(['/chat', userName]);
  }
}
