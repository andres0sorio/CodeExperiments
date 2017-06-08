import { Component } from '@angular/core';
//import { Book } from '../models/book';

@Component({
  templateUrl: './fiches.component.html',
  styleUrls: ['./fiches.component.css']
})
export class FichesComponent { }

/*
  constructor(private router: Router, private route: ActivatedRoute) { };

  goToProduct(id: string): void {
    this.router.navigate(['./', id], {relativeTo: this.route});
  }
  */

/*
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
*/
  /*
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
      console.log('book title:', output.value['title']);
    }
  
    revert() { this.ngOnChanges(); }
  


}

  */