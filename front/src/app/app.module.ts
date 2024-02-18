import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatCardModule} from '@angular/material/card';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatTabsModule} from '@angular/material/tabs';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import {MatGridListModule} from '@angular/material/grid-list';
import {NavbarComponent} from './tools/navbar/navbar.component';
import {HomeComponent} from './page/home/home.component';
import {ProfileComponent} from './page/profile/profile.component';
import {LoginComponent} from './page/login/login.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from '@angular/common/http';
import {TokenInterceptorProvider} from "./_helpers/token.interceptor";
import {MatTableModule} from "@angular/material/table";
import {MatListModule} from "@angular/material/list";
import {MatNativeDateModule, MatRippleModule} from "@angular/material/core";
import {SearchComponent} from './page/search/search.component';
import {MatAutocompleteModule} from "@angular/material/autocomplete";
import {MatInputModule} from "@angular/material/input";
import {MatChipsModule} from "@angular/material/chips";
import {NgOptimizedImage} from "@angular/common";
import {ContactComponent} from './page/contact/contact.component';
import {NewPostComponent} from './page/new-post/new-post.component';
import {SignUpComponent} from './page/sign-up/sign-up.component';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MapComponent} from "./page/map/map.component";

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    HomeComponent,
    ProfileComponent,
    LoginComponent,
    NewPostComponent,
    ContactComponent,
    SearchComponent,
    SignUpComponent,
    MapComponent
  ],
  imports: [
    MatGridListModule,
    MatCardModule,
    MatSidenavModule,
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    MatFormFieldModule,
    MatTabsModule,
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    MatButtonToggleModule,
    MatTableModule,
    MatListModule,
    MatRippleModule,
    MatAutocompleteModule,
    MatInputModule,
    ReactiveFormsModule,
    MatChipsModule,
    NgOptimizedImage,
    MatSnackBarModule,
    MatNativeDateModule,
    MatDatepickerModule,
  ],
  providers: [
    TokenInterceptorProvider,
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
