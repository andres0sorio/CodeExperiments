import { Injectable } from '@angular/core';
import { Http, RequestOptions, Request, Response, Headers, ResponseContentType } from '@angular/http';
import { AuthHttp } from 'angular2-jwt';

import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

import * as FileSaver from 'file-saver';

import { LoaderService } from './loader/loader.service';
import { MessageService } from '../services/message.service';
import { Fiche } from "../models/fiche";
import { Book } from "../models/book";
import { IFiche } from "../models/interfaces";

@Injectable()
export class FicheDataService {

  private backendUrl = 'https://fast-sea-84532.herokuapp.com';  // URL to web API 
  private svcDocxUrl = 'https://secure-fjord-78923.herokuapp.com'; //URL to docx API

  //private svcDocxUrl = 'http://localhost:4567';
  //private backendUrl = 'http://localhost:4567';

  contentHeaders = new Headers();

  constructor(private http: Http,
    public authHttp: AuthHttp,
    private messageService: MessageService,
    private loaderService: LoaderService) {

    this.contentHeaders.append('Accept', 'application/json');
    this.contentHeaders.append('Content-Type', 'application/json');

  }

  //POST 1.
  createFiche(user: string, data: any) {

    this.loaderService.show();

    let options = new RequestOptions({ headers: this.contentHeaders });

    if (1) {

      let comments = data['comments'];
      delete data['comments'];
      let ficheInfo: any = { "id": 0, "book": data, "comments": comments };
      let fiche = new Fiche(ficheInfo);
      
      console.log(JSON.stringify(fiche));

      this.authHttp.post(this.backendUrl + "/users/fiches/", JSON.stringify(fiche), options)
        .map((res: Response) => {
          let message = res.json();
          if ((message.errorInd === false) && message.value) {
            this.messageService.sendMessage('success', "New fiche saved!");
            setTimeout(function () {
              this.messageService.clearMessage();
            }.bind(this), 4500);
          }
        })
        .catch((error: any) => Observable.throw(error.json().error || 'Server error'))
        .finally(() => {
        this.loaderService.hide()})
        .subscribe();
    }
  }

  //POST 2.
  updateFiche(id: string, uuid: string, data: any) {

    this.loaderService.show();

    let options = new RequestOptions({ headers: this.contentHeaders });

    if (1) {

      let comments = data['comments'];
      delete data['comments'];
      data.book_uuid = uuid;
      let ficheInfo: any = { "id": id, "book": data, "comments": comments };
      let fiche = new Fiche(ficheInfo);
      
      console.log(JSON.stringify(fiche));

      return this.authHttp.put(this.backendUrl + "/users/fiches/" + id + "/" + uuid, JSON.stringify(fiche), options)
        .map((res: Response) => {
          let message = res.json();
          if ((message.errorInd === false) && message.value) {
            this.messageService.sendMessage('success', "Fiche updated!");
            setTimeout(function () {
              this.messageService.clearMessage();
            }.bind(this), 4500);
          }
          return message;
        })
        .catch((error: any) => Observable.throw(error.json().error || 'Server error')).finally(() => {
        this.loaderService.hide()});
    }
  }

  //POST 3.
  createFicheDocx(data: any, uuid : string) {

    this.loaderService.show();

    let options = new RequestOptions({ headers: this.contentHeaders });

    data.book_uuid = uuid;

    if (1) {

      let comments = data['comments'];
      delete data['comments'];
      let ficheInfo: any = { "id": 0, "book": data, "comments": comments };
      let fiche = new Fiche(ficheInfo);

      console.log(JSON.stringify(fiche));
      
      return this.authHttp.post(this.svcDocxUrl + "/users/fiches/", JSON.stringify(fiche), options)
        .map((res: Response) => {
          let message = res.json();
          if ((message.errorInd === false) && message.value) {
            this.messageService.sendMessage('success', "Word document created!");
            setTimeout(function () {
              this.messageService.clearMessage();
            }.bind(this), 4500);
          }
          return message;
        })
        .catch((error: any) => Observable.throw(error.json().error || 'Server error')).finally(() => {
        this.loaderService.hide()});
    }
  }

  // GET 1.
  getStoredFiches(): Observable<Fiche[]> {

    this.loaderService.show();

    let options = new RequestOptions({ headers: this.contentHeaders });

    return this.authHttp.get(this.backendUrl + "/users/fiches/", options)
      .map(this.extractData)
      .catch(this.handleError).finally(() => {
        this.loaderService.hide();
      });
  }

  //GET 1. method 2
  getStoredIFiches(): Observable<IFiche[]> {

    return this.authHttp.get(this.backendUrl + "/users/fiches/")
      .map(response => response.json() as IFiche[])
      .catch(this.handleError).finally(() => {
        this.loaderService.hide()});
  }

  // GET 2.
  getStoredFiche(id: string, uuid: string): Observable<Fiche> {
    
    this.loaderService.show();

    return this.authHttp.get(this.backendUrl + "/users/fiches/" + id + "/" + uuid)
      .map(this.extractData)
      .catch(this.handleError).finally(() => {
        this.loaderService.hide()});
  }

  // GET 3.
  getFicheDocx(uuid: String) {

    this.loaderService.show();

    let filename = "fiche_" + uuid + ".docx";
    
    this.authHttp.get(this.svcDocxUrl + "/users/fiches/")
      .map(this.extractData)
      .catch(this.handleError)
      .finally(() => {
        this.loaderService.hide()})
      .subscribe(
      data => {
        if (!data.errorInd) {
          let byteCharacters = atob(data.value);
          let byteNumbers = new Array(byteCharacters.length);
          for (var i = 0; i < byteCharacters.length; i++)
            byteNumbers[i] = byteCharacters.charCodeAt(i);
          let byteArray = new Uint8Array(byteNumbers);
          let file = new Blob([byteArray], { type: 'application/octet-stream' });
          FileSaver.saveAs(file, filename);
        }
      })

  }

  // GET 4.
  getFichePNG() {

    this.http.get(this.svcDocxUrl + "/serialnumbers")
      .map(this.extractData)
      .catch(this.handleError).subscribe(
      data => {
        let byteCharacters = atob(data.barcodeImg);
        let byteNumbers = new Array(byteCharacters.length);
        for (var i = 0; i < byteCharacters.length; i++)
          byteNumbers[i] = byteCharacters.charCodeAt(i);
        let byteArray = new Uint8Array(byteNumbers);
        let file = new Blob([byteArray], { type: 'image/png' });
        FileSaver.saveAs(file, 'helloworld.png');
      })

  }

  // DELETE 1.
  deleteStoredFiche(id: string, uuid: string) {

    this.loaderService.show();

    this.authHttp.delete(this.backendUrl + "/users/fiches/" + id + "/" + uuid)
      .map((res: Response) => {
        let message = res.json();
        if ((message.errorInd === false) && message.value) {
          this.messageService.sendMessage('success', "Fiche deleted!");
          setTimeout(function () {
            this.messageService.clearMessage();
          }.bind(this), 4500);
        }
      })
      .catch(this.handleError).finally(() => {
        this.loaderService.hide()})
      .subscribe();

  }

  private extractData(res: Response) {
    let body = res.json();
    console.log("Body: ", body);
    return body;
  }

  private handleError(error: Response | any) {
    // In a real world app, you might use a remote logging infrastructure
    let errMsg: string;
    if (error instanceof Response) {
      const body = error.json() || '';
      const err = body.error || JSON.stringify(body);
      errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
    } else {
      errMsg = error.message ? error.message : error.toString();
    }
    console.error("handleError:", errMsg);
    return Observable.throw(errMsg);
  }

}
