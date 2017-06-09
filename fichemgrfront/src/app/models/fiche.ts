import { Book } from './book';
import { Comment } from './comment';

export class Fiche {

    id: number;
    abook: Book;
    acomment : Comment;

    constructor(values: Object = {}) {
        this.id = values['id'];
        this.abook = new Book(values['book']);
    }

}
