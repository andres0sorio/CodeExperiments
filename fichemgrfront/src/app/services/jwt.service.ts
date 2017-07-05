import { Injectable } from '@angular/core';

@Injectable()
export class JwtService {

  constructor() { }

  getToken(): any {
    return "";
  }

}

export const JWT_PROVIDERS: Array<any> = [
  { provide: JwtService, useClass: JwtService }
];