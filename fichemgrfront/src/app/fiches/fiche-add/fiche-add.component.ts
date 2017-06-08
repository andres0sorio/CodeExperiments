import { Component, OnInit, OnChanges, Input } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Book } from '../../models/book';

@Component({
  selector: 'app-fiche-add',
  templateUrl: './fiche-add.component.html',
  styleUrls: ['./fiche-add.component.css']
})
export class FicheAddComponent implements OnInit {

  bookForm: FormGroup;

  constructor(fb: FormBuilder) {

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

}
