import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './page/home/home.component';
import {ChatComponent} from "./page/chat/chat.component";
import { ProfileComponent } from './page/profile/profile.component';
import {LoginComponent} from "./page/login/login.component";
import {SearchComponent} from "./page/search/search.component";
import { ContactComponent } from './page/contact/contact.component';
import { NewPostComponent } from './new-post/new-post.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'profile', component: ProfileComponent},
  { path: 'home', component: HomeComponent },
  { path: 'chat/:username', component: ChatComponent },
  { path: 'profile', component: ProfileComponent},
  { path: 'login', component: LoginComponent},
  { path: 'add', component: NewPostComponent},
  { path: 'contact', component: ContactComponent},
  { path: 'search', component: SearchComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
