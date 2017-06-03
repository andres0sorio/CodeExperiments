import { Document } from './document.model';

export class Project extends Document {

    _name: string;
    _location: string;
    _budget: number;
    _actorType: string;
    _responsabilities: string;

    constructor(name : string, location : string, budget : number, actorType : string, responsabilities : string ) {
        super();
        this._name = name;
        this._location = location;
        this._budget = budget;
        this._actorType = actorType;
        this._responsabilities = responsabilities;
    }


  get name(): string {
    return this._name;
  }

  set name(value: string) {
    this._name = value;
  }

  get location(): string {
    return this._location;
  }

  set location(value: string) {
    this._location = value;
  }

  get budget(): number {
    return this._budget;
  }

  set budget(value: number) {
    this._budget = value;
  }

  get actorType(): string {
    return this._actorType;
  }

  set actorType(value: string) {
    this._actorType = value;
  }

  get responsabilities(): string {
    return this._responsabilities;
  }

  set responsabilities(value: string) {
    this._responsabilities = value;
  }
}
