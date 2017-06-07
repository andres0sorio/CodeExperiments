import { Book } from './book';

export class Fiche {

    id: number;
    abook: Book;

    constructor(id: number, values: Object = {}) {
        this.id = id;
        this.abook = new Book(values);
    }

}
