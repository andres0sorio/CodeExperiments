import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Fiche } from "../../models/fiche";
import { FicheDataService } from '../../services/fiche-data.service';
import { FormBuilder, FormGroup, FormArray } from '@angular/forms';

@Component({
  selector: 'app-fiche-detail',
  templateUrl: './fiche-detail.component.html',
  styleUrls: ['./fiche-detail.component.css']
})
export class FicheDetailComponent implements OnInit {

  id: string;
  uuid: string;
  fiche: Fiche;
  ficheForm: FormGroup;

  constructor( 
    private service: FicheDataService,
    private router: Router,
    private route: ActivatedRoute,
    private fb: FormBuilder ) {

    this.ficheForm = fb.group({
      title: 'X',
      subTitle: 'X',
      author: 'X',
      yearPub: 0,
      editor: 'X',
      collection: 'X',
      pages: 0,
      language: 'X',
      comments: fb.array([this.initComment()])
    });

  }

  initComment() {
    return this.fb.group(
      {
        aboutAuthor: '',
        aboutGenre: '',
        aboutCadre: '',
        aboutCharacters: '',
        resume: '',
        extrait: '',
        appreciation: ''
      }
    );
  }

  ngOnInit() {
    this.route.params.subscribe(params => { this.id = params['id']; this.uuid = params['uuid']; });
    this.service.getStoredFiche(this.id, this.uuid).subscribe((retrieveFiche: Fiche) => {

      this.fiche = retrieveFiche;

      this.ficheForm.patchValue({
        title: this.fiche.book.title,
        subTitle: this.fiche.book.subTitle,
        author: this.fiche.book.author,
        yearPub: this.fiche.book.yearPub,
        editor: this.fiche.book.editor,
        collection: this.fiche.book.collection,
        pages: this.fiche.book.pages,
        language: this.fiche.book.language
      });

      if (this.fiche.comments)
        this.removeComment(0);

      this.fiche.comments.forEach((element) => {
        this.addStoredComment(element);
      });

    });
  }

  addComment() {
    const control = <FormArray>this.ficheForm.controls['comments'];
    control.push(this.initComment());
  }

  addStoredComment(comment: any) {
    const control = <FormArray>this.ficheForm.controls['comments'];
    control.push(this.fb.group(comment));
  }

  removeComment(i: number) {
    const control = <FormArray>this.ficheForm.controls['comments'];
    control.removeAt(i);
  }

  onSubmit(output: FormGroup): void {
    console.log('you submitted value: ', output.value);
    //this.service.addFiche(output.value);
    this.service.updateFiche(this.id, this.uuid, output.value);
  }

  gotoFiches() {
    /*
    let heroId = this.hero ? this.hero.id : null;
    // Pass along the hero id if available
    // so that the HeroList component can select that hero.
    // Include a junk 'foo' property for fun.
    */
    let ficheId = this.id;
    this.router.navigate(['/fiches/list', { id: ficheId }]);

  }

}
