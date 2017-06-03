import { Component, OnInit, OnChanges, Input } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Project } from '../models/project.model';

@Component({
  selector: 'app-add-project',
  templateUrl: './add-project.component.html',
  styleUrls: ['./add-project.component.css']
})
export class AddProjectComponent implements OnInit {

  myForm: FormGroup;

  @Input()
  aproject: Project;

  constructor(fb: FormBuilder) {

    this.myForm = fb.group({
      name: '',
      location: '',
      budget: 0,
      actor: '',
      responsabilities: ''
    });
  }

  ngOnInit() {
  }

  ngOnChanges() {
    this.myForm.setValue({
      name: this.aproject.name,
    });
  }

  onSubmit(output: FormGroup): void {
    console.log('you submitted value: ', output.value);
    console.log('project name:', output.value['name']);
  }

  revert() { this.ngOnChanges(); }

}
