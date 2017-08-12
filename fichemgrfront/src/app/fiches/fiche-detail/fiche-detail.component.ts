import { Component, OnInit, OnChanges } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Fiche } from "../../models/fiche";
import { FicheDataService } from '../../services/fiche-data.service';
import { AuthService } from '../../services/auth.service';
import { FormBuilder, FormGroup, FormArray } from '@angular/forms';
import { Subscription } from 'rxjs/Subscription';
import { MessageService } from '../../services/message.service';
import { Observable } from 'rxjs/Rx';
import { LocaleService } from '../../services/locale.service';

@Component({
  selector: 'app-fiche-detail',
  templateUrl: './fiche-detail.component.html',
  styleUrls: ['./fiche-detail.component.css']
})
export class FicheDetailComponent implements OnInit, OnChanges {

  public labels : any;
  public bookform : any;
  public commentform : any;

  id: string;
  uuid: string;
  fiche: Fiche;
  ficheForm: FormGroup;
  author: string;
  docx: boolean;
  isupdated : boolean;

  message: any;
  subscription: Subscription;

  constructor(
    private service: FicheDataService,
    private router: Router,
    private route: ActivatedRoute,
    private fb: FormBuilder,
    private authService: AuthService,
    private messageService: MessageService,
    private locale : LocaleService) {

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
      comments: fb.array([this.initComment(this.author)])
    });

    this.subscription = this.messageService.getMessage().subscribe(message => { this.message = message; });

    this.docx = false;

    this.isupdated = false;

    this.labels = locale.get("fiches");
    this.bookform = locale.get("bookform");
    this.commentform = locale.get("commentform");
    
  }

  initComment(currentAuthor: string) {
    return this.fb.group(
      {
        author: currentAuthor,
        aboutAuthor: '',
        aboutGenre: '',
        aboutCadre: '',
        aboutCharacters: '',
        resume: '',
        extrait: '',
        appreciation: '',
        isCompleted: false
      }
    );
  }

  ngOnInit() {

    console.log('OnInit');

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

  ngOnChanges() {

    console.log("OnChanges");
    
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

      this.removeAllComments();

      this.fiche.comments.forEach((element) => {
        this.addStoredComment(element);
      });

    });
  }

  addComment() {
    const control = <FormArray>this.ficheForm.controls['comments'];
    control.push(this.initComment(this.author));
  }

  addStoredComment(comment: any) {
    const control = <FormArray>this.ficheForm.controls['comments'];
    control.push(this.fb.group(comment));
  }

  removeComment(i: number) {
    const control = <FormArray>this.ficheForm.controls['comments'];
    control.removeAt(i);
  }

  removeAllComments() {
    console.log("cleaning comments from current form");
    const control = <FormArray>this.ficheForm.controls['comments'];
    for (var i = 0; i < control.length; i++)
      control.removeAt(i);

  }

  onSubmit(output: FormGroup): void {
    console.log('you submitted value: ', output.value);
    this.service.updateFiche(this.id, this.uuid, output.value).subscribe(data => {
      if (!data.errorInd) {
        this.isupdated = true;
        this.ngOnChanges();
      }
    });

  }

  gotoFiches() {
    let ficheId = this.id;
    this.router.navigate(['/fiches/list', { id: ficheId }]);

  }

  createFicheDocx() {
    console.log('you submitted value: ', this.ficheForm.value);
    this.service.createFicheDocx(this.ficheForm.value, this.uuid).subscribe(data => {
      if (!data.errorInd) {
        this.docx = true;
        this.ngOnChanges();
      }
    });

  }

  downloadFicheDocx() {
    console.log('download file button');
    this.service.getFicheDocx(this.uuid);
    this.docx = false;
    this.isupdated = false;
  }

  revert() { this.ngOnChanges(); }

  ngOnDestroy() {
    console.log('destroying');
    // unsubscribe to ensure no memory leaks
    this.subscription.unsubscribe();
  }

}
