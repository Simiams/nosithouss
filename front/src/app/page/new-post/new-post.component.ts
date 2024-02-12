import {Component} from '@angular/core';
import {choiceNewPost, EPostTypeStr} from "../../_interfaces/ennums";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {PostService} from "../../_service/post.service";
import {AssetsService} from "../../_service/assets.service";

@Component({
  selector: 'app-new-post',
  templateUrl: './new-post.component.html',
  styleUrls: ['./new-post.component.css']
})
export class NewPostComponent {
  selectedChip: choiceNewPost = choiceNewPost.POST;
  createPostForm: FormGroup;
  choiceNewPost = choiceNewPost;
  protected readonly Object = Object;

  constructor(private fb: FormBuilder, private postService: PostService, private assetsService: AssetsService) {
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

  loadFile = (event: Event) => {
    const input = event.target as HTMLInputElement;
    const image = document.getElementById('output') as HTMLImageElement;

    if (input.files && input.files[0]) {
      image.src = URL.createObjectURL(input.files[0]);
    }
  };


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

}
