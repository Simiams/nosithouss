import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from './page/home/home.component';
import {ChatComponent} from "./page/chat/chat.component";
import {ProfileComponent} from './page/profile/profile.component';
import {LoginComponent} from "./page/login/login.component";
import {SearchComponent} from "./page/search/search.component";
import {ContactComponent} from './page/contact/contact.component';
import {NewPostComponent} from './page/new-post/new-post.component';
import {SignUpComponent} from './page/sign-up/sign-up.component';
import {AuthGuard} from "./_helpers/auth.guard";
import {MapComponent} from "./page/map/map.component";

const routes: Routes = [
  {path: '', component: HomeComponent, canActivate: [AuthGuard]},
  {path: 'profile', component: ProfileComponent, canActivate: [AuthGuard]},
  {path: 'home', component: HomeComponent, canActivate: [AuthGuard]},
  {path: 'chat/:username', component: ChatComponent, canActivate: [AuthGuard]},
  {path: 'profile', component: ProfileComponent, canActivate: [AuthGuard]},
  {path: 'login', component: LoginComponent},
  {path: 'signup', component: SignUpComponent},
  {path: 'add', component: NewPostComponent, canActivate: [AuthGuard]},
  {path: 'contact', component: ContactComponent, canActivate: [AuthGuard]},
  {path: 'search', component: SearchComponent, canActivate: [AuthGuard]},
  {path: 'map', component: MapComponent, canActivate: [AuthGuard]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
