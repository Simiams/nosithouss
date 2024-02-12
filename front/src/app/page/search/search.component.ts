import {Component} from '@angular/core';
import {Router} from "@angular/router";
import {UserService} from "../../_service/user.service";
import {choiceSearch, EPostType, EPostTypeStr} from "../../_interfaces/ennums";
import {PostService} from "../../_service/post.service";

interface autocompleteType {
  value: string,
  img: string
}

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css'],
})
export class SearchComponent {
  assetsBaseUrl = "http://localhost:8080/api/assets/" //todo global
  protected readonly choiceSearch = choiceSearch;
  protected readonly Object = Object;
  inputValue: string = "";
  possibleValues: autocompleteType[] = []
  private router!: Router;
  green: string = "rgba(78,94,69,0.46)";
  private userService: UserService
  selectedChip: choiceSearch = choiceSearch.USER;

  constructor(userService: UserService, router: Router, private postService: PostService) {
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
        case choiceSearch.CATALOG:
          this.autocompletePost(inputChange, EPostTypeStr.CATALOG)
          break;
        case choiceSearch.POST:
          this.autocompletePost(inputChange, EPostTypeStr.POST)
          break;
        case choiceSearch.GUARDINGPOST:
          this.autocompletePost(inputChange, EPostTypeStr.GUARDING)
          break;
      }
    }
  }

  autocompleteUserName(currentValue: string) {
    this.userService.getAutocomplete(currentValue).subscribe(
      data => this.possibleValues = data.map(d => ({value: d.userName, img: d.pdp})),
      err => console.error(err)
    )
  }

  autocompletePost(currentValue: string, type: EPostTypeStr) {
    this.postService.getAutocomplete(currentValue, type).subscribe(
      data => this.possibleValues = data.map(d => ({value: d.title, img: d.img})),
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
