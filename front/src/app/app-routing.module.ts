import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import {ChatComponent} from "./chat/chat.component";
import { ProfileComponent } from './profile/profile.component';
import {LoginComponent} from "./login/login.component";
import {ContactComponent} from "./contact/contact.component";

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'profile', component: ProfileComponent},
  { path: 'home', component: HomeComponent },
  { path: 'chat/:username', component: ChatComponent },
  { path: 'profile', component: ProfileComponent},
  { path: 'login', component: LoginComponent},
  { path: 'contact', component: ContactComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
