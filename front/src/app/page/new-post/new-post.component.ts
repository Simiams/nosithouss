import {Component} from '@angular/core';
import {choiceNewPost, EPostTypeStr} from "../../_interfaces/ennums";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {PostService} from "../../_service/post.service";
import {AssetsService} from "../../_service/assets.service";
import {OsmService} from "../../_service/osm.service";
import {IOsmGet} from "../../_interfaces/post";
import {MatAutocompleteSelectedEvent} from "@angular/material/autocomplete";

@Component({
  selector: 'app-new-post',
  templateUrl: './new-post.component.html',
  styleUrls: ['./new-post.component.css']
})
export class NewPostComponent {
  selectedChip: choiceNewPost = choiceNewPost.POST;
  createPostForm: FormGroup;
  choiceNewPost = choiceNewPost;
  newOptions: IOsmGet[] = []
  protected readonly Object = Object;

  constructor(private fb: FormBuilder, private postService: PostService, private assetsService: AssetsService, private osmService: OsmService) {
    this.createPostForm = this.fb.group({
      type: EPostTypeStr.POST,
      content: ['', Validators.required],
      title: ['', Validators.required],
      lastVersion: [null],
      nbLike: [0, Validators.min(0)],
      nbDisLike: [0, Validators.min(0)]
    });
  }

  onChipSelectionChange() {
    switch (this.selectedChip) {
      case choiceNewPost.CATALOG:
        this.createPostForm = this.fb.group({
          type: EPostTypeStr.CATALOG,
          content: ['', Validators.required],
          title: ['', Validators.required],
          lastVersion: [null],
          nbLike: [0, Validators.min(0)],
          nbDisLike: [0, Validators.min(0)],
          img: this.fb.array([]),
        });
        break;
      case choiceNewPost.GUARDINGPOST:
        this.createPostForm = this.fb.group({
          type: EPostTypeStr.GUARDING,
          content: ['', Validators.required],
          title: ['', Validators.required],
          lastVersion: [null],
          nbLike: [0, Validators.min(0)],
          nbDisLike: [0, Validators.min(0)],
          img: this.fb.array([]),
          guardingAt: ['', Validators.required],
          endGuardingAt: ['', Validators.required],
          address: ['', Validators.required],
          coordinateX: [0, Validators.required],
          coordinateY: [0, Validators.required]
        });
        break;
      case choiceNewPost.POST:
        this.createPostForm = this.fb.group({
          type: EPostTypeStr.POST,
          content: ['', Validators.required],
          title: ['', Validators.required],
          lastVersion: [null],
          nbLike: [0, Validators.min(0)],
          nbDisLike: [0, Validators.min(0)]
        });
        break;
    }
  }

  onSubmit() {
    const fileInput = <HTMLInputElement>document.getElementById('fileInput');
    const file = fileInput.files !== null ? fileInput.files![0] : null;
    let fileName

    if (this.createPostForm.valid && file !== null) {
      this.assetsService.uploadFile(file).subscribe(
        response => {
          fileName = response.fileName

          const postData = {...this.createPostForm.value, img: [fileName]};
          console.log(postData)
          this.postService.createPost(postData).subscribe(
            response => {
            },
            error => {
              console.error('Erreur lors de la création du post', error);
            }
          );

        },
        error => console.error(error)
      )


    }
  }

  autocompleteAddressChange() {
    if (this.createPostForm.value.address.length > 5) {
      this.osmService.getAutocomplete(this.createPostForm.value.address).subscribe(
        data => {
          this.newOptions = data
        }
      );
    }
  }

  onOptionSelected($event: MatAutocompleteSelectedEvent) {
    let selectedOption = this.newOptions.find(objet => objet.display_name === $event.option.value);
    this.createPostForm.value.coordinateX = selectedOption?.lon
    this.createPostForm.value.coordinateY = selectedOption?.lat
    console.log(selectedOption)
    console.log(this.createPostForm.value)
  }
}
