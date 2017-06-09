import { Book } from './book';
import { Comment } from './comment';

export class Fiche {

    id: number;
    book: Book;
    comment : Comment;

    constructor(values: Object = {}) {
        console.log("Constructor with:", values['id']);
        this.id = values['id'];
        this.book = new Book(values['book']);
    }

}
