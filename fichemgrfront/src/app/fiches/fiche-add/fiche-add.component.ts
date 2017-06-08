import { Component, OnInit, OnChanges, Input } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Book } from '../../models/book';
import { FicheDataService } from '../../services/fiche-data.service';

@Component({
  selector: 'app-fiche-add',
  templateUrl: './fiche-add.component.html',
  styleUrls: ['./fiche-add.component.css']
})
export class FicheAddComponent implements OnInit {

  bookForm: FormGroup;

  constructor(private service: FicheDataService, fb: FormBuilder) {

    this.bookForm = fb.group({
      title: '',
      subTitle: '',
      author: '',
      yearPub: 0,
      editor: '',
      collection: '',
      pages: 0,
      language: ''
    });

  }

  ngOnInit() {
  }

  ngOnChanges() {
    this.bookForm.setValue({
      title: '',
      subTitle: '',
      author: '',
      yearPub: 0,
      editor: '',
      collection: '',
      pages: 0,
      language: ''
    });
  }

  onSubmit(output: FormGroup): void {
    console.log('you submitted value: ', output.value);
    console.log('book name:', output.value['title']);
    this.service.addFiche(output.value);
  }

  revert() { this.ngOnChanges(); }

}
