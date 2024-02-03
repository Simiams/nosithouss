import { Component, OnInit } from '@angular/core';
import { ICredential } from 'src/app/_interfaces/credential';
import {AuthService} from "../_service/auth.service";
import {TokenService} from "../_service/token.service";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  form: ICredential = {
    userName: '',
    password: ''
  }

  constructor(
    private authService: AuthService,
    private tokenService: TokenService
  ) { }

  // ngOnInit(): void {
  // }

  onSubmit(): void{
    console.log("login component")
    console.log(this.form)
    this.authService.login(this.form).subscribe(
      data => {
        console.log("token from onSubmit: " + data.bearer)
        this.tokenService.saveToken(data.bearer)
      },
      err => console.error(err)
    )
  }
}
