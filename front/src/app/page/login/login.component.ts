import {Component} from '@angular/core';
import {ICredential} from 'src/app/_interfaces/credential';
import {AuthService} from 'src/app/_service/auth.service';
import {TokenService} from 'src/app/_service/token.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  error: string = ""
  form: ICredential = {
    userName: '',
    password: ''
  }

  constructor(
    private _snackBar: MatSnackBar,
    private authService: AuthService,
    private tokenService: TokenService,
    private router: Router
  ) { }

  onSubmit(): void{
    console.log(this.form)
    this.authService.login(this.form).subscribe(
      data => {
        this.tokenService.saveToken(data.bearer)
        this.router.navigate(['home'])
      },
      err => this._snackBar.open('Username or password incorrect', 'Fermer', {
        duration: 2000,
      })
    )
  }
}
