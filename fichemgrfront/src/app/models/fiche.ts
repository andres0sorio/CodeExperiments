import { Book } from './book';
import { Comment } from './comment';

export class Fiche {

    id: number;
    abook: Book;
    acomment : Comment;

    constructor(id: number, values: Object = {}) {
        this.id = id;
        this.abook = new Book(values);
    }

}
