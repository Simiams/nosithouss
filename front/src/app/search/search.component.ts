import {Component} from '@angular/core';
import {Router} from "@angular/router";
import {UserService} from "../_service/user.service";
import {IUsernameGet} from "../_interfaces/user";
import {choiceSearch} from "../_interfaces/ennums";

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css'],
})
export class SearchComponent {
  protected readonly choiceSearch = choiceSearch;
  protected readonly Object = Object;
  inputValue: string = "";
  possibleValues: IUsernameGet[] = []
  private router!: Router;
  green: string = "rgba(78,94,69,0.46)";
  private userService: UserService
  selectedChip: choiceSearch = choiceSearch.USER;

  constructor(userService: UserService, router: Router) {
    this.router = router
    this.userService = userService
  }

  onSearchInputChange(event: any) {
    this.inputValue = event.target.value
    this.search(event.target.value)
  }

  search(inputChange: string) {
    if (inputChange.length > 2) {
      switch (this.selectedChip) {
        case choiceSearch.USER:
          this.autocompleteUserName(inputChange)
          break;
        case choiceSearch.FORUM:
          console.log("form")
          this.possibleValues = []
          break;
        case choiceSearch.POST:
          this.possibleValues = []
          break;
        case choiceSearch.GUARDINGPOST:
          this.possibleValues = []
          break;
      }
    }
  }

  autocompleteUserName(currentValue: string) {
    this.userService.getAutocomplete(currentValue).subscribe(
      data => this.possibleValues = data,
      err => console.error(err)
    )
  }

  redirectToProfile(userName: string) {
    this.router.navigate(['/profile', userName]);
  }


  onChipSelectionChange() {
    this.search(this.inputValue)
  }

  protected readonly status = status;
}
