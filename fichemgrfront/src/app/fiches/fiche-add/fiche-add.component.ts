import { Component, OnInit, OnChanges, Input } from '@angular/core';
import { FormBuilder, FormGroup, FormArray } from '@angular/forms';
import { FicheDataService } from '../../services/fiche-data.service';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-fiche-add',
  templateUrl: './fiche-add.component.html',
  styleUrls: ['./fiche-add.component.css']
})
export class FicheAddComponent implements OnInit {

  author : string;
  ficheForm: FormGroup;

  constructor(private service: FicheDataService, private fb: FormBuilder, private authService: AuthService) {

    this.author = this.authService.getUser().split('@')[0];
    
    this.ficheForm = fb.group({
      title: '',
      subTitle: '',
      author: '',
      yearPub: 0,
      editor: '',
      collection: '',
      pages: 0,
      language: '',
      comments: fb.array([this.initComment()])
    });
  }

  ngOnInit() {
  }

  initComment() {
    return this.fb.group({
      aboutAuthor: '',
      aboutGenre: '',
      aboutCadre: '',
      aboutCharacters: '',
      resume: '',
      extrait: '',
      appreciation: '',
      isCompleted: false
    });
  }

  addComment() {

    const control = <FormArray>this.ficheForm.controls['comments'];
    control.push(this.initComment());
  }

  removeComment(i: number) {
    // remove address from the list
    const control = <FormArray>this.ficheForm.controls['comments'];
    control.removeAt(i);
  }

  ngOnChanges() {
    this.ficheForm.setValue({
      title: '',
      subTitle: '',
      author: '',
      yearPub: 0,
      editor: '',
      collection: '',
      pages: 0,
      language: '',
      comments: []
    });
  }

  onSubmit(output: FormGroup): void {
    console.log('you submitted value: ', output.value);
    this.service.createFiche(this.author, output.value);
  }

  revert() { this.ngOnChanges(); }

}
