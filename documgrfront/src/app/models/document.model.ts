export class Document {

	_type : string;
	_code : string;
	_number : string;
	_docDb : number;
	_year : number;
	_firm : string;
	_division : string;
	_actor : string;

    constructor () {
        this._type = "IN";
        this._code = "IN";
        this._number = "101";
        this._docDb = 1001;
        this._year = 2017;
        this._firm = "Phsytech";
        this._division = "R&D";
        this._actor = "AOsorio";

    }


  get type(): string {
    return this._type;
  }

  set type(value: string) {
    this._type = value;
  }

  get code(): string {
    return this._code;
  }

  set code(value: string) {
    this._code = value;
  }

  get number(): string {
    return this._number;
  }

  set number(value: string) {
    this._number = value;
  }

  get docDb(): number {
    return this._docDb;
  }

  set docDb(value: number) {
    this._docDb = value;
  }

  get year(): number {
    return this._year;
  }

  set year(value: number) {
    this._year = value;
  }

  get firm(): string {
    return this._firm;
  }

  set firm(value: string) {
    this._firm = value;
  }

  get division(): string {
    return this._division;
  }

  set division(value: string) {
    this._division = value;
  }

  get actor(): string {
    return this._actor;
  }

  set actor(value: string) {
    this._actor = value;
  }
}
