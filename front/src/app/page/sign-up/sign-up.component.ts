import { Component } from '@angular/core';
import { ICredential } from 'src/app/_interfaces/credential';
import { AuthService } from 'src/app/_service/auth.service';
import { TokenService } from 'src/app/_service/token.service';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent {
  form: ICredential = {
    userName: '',
    password: ''
  }

  constructor(
    private authService: AuthService,
    private tokenService: TokenService) {
    
  }

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
