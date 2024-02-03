import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import {ITokenUser} from "../_interfaces/user";
import {jwtDecode} from "jwt-decode";

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  constructor(private router: Router) { }

  saveToken(token: string): void{
    console.log("save token")
    console.log("token: " + token)
    localStorage.setItem('token', token)
    console.log("tokenfrom localstorage: " + localStorage.getItem('token'))
    this.router.navigate(['admin'])
  }

  isLogged(): boolean{
    console.log("isLogged from token service")
    const token = localStorage.getItem('token')
    return !! token
  }

  clearToken(): void{
    console.log("clearToken")
    localStorage.removeItem('token')
    this.router.navigate(['/'])
  }

  clearTokenExpired(): void{
    console.log("clearTokenExpired")
    localStorage.removeItem('token')
    this.router.navigate(['auth'])
  }

  getToken(): string | null{
    console.log("getToken")
    console.log("token from localStorage: " + localStorage.getItem('token'))
    return localStorage.getItem('token')
  }

  getPayload(){
    console.log("getPayLoad")
    let user: ITokenUser = {
      id: 0,
      nom: '',
      prenom: '',
      email: ''
    }

    let token = localStorage.getItem('token')
    if(token != null){
      const decode: ITokenUser =  jwtDecode<ITokenUser>(token)
      user.id = decode.id
      user.nom = decode.nom
      user.prenom = decode.prenom
      user.email = decode.email
    }

    return user

  }
}
