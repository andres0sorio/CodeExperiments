import { Component, OnInit, OnChanges, Input } from '@angular/core';
import { FormBuilder, FormGroup, FormArray } from '@angular/forms';
import { FicheDataService } from '../../services/fiche-data.service';
import { AuthService } from '../../services/auth.service';

import { Fiche } from '../../models/fiche';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  errorMessage: string;
  storedFiches: Fiche[];
  selectedId: number;
  selectedUuid: string;
  userForm: FormGroup;

  public roles = [
    { value: 'admin', display: 'Administrator' },
    { value: 'user', display: 'User' }
  ];

  constructor(private authService: AuthService, private fBuilder: FormBuilder, private service: FicheDataService) {

    this.userForm = fBuilder.group({
      username: '',
      password: '',
      role: this.roles[0].value
    });

  }

  ngOnInit() {
  }

  ngOnChanges() {
    this.userForm.setValue({
      username: '',
      password: '',
      role: this.roles[0].value
    });
  }

  onSubmit(output: FormGroup): void {
    console.log('you submitted value: ', output.value);
    this.authService.createUser(output.value);
  }

  revert() { this.ngOnChanges(); }

  isSelected(fiche: Fiche) {
    return fiche.id === this.selectedId;
  }

  onSelect(fiche: Fiche) {
    this.selectedId = fiche.id;
    this.selectedUuid = fiche.fiche_uuid;
    this.service.deleteStoredFiche(fiche.id.toString(), fiche.fiche_uuid);
    this.getStoredFiches();
  }

  getStoredFiches() {
    this.service.getStoredFiches()
      .subscribe(
      storedFiches => this.storedFiches = storedFiches,
      error => this.errorMessage = <any>error);
  }



}
