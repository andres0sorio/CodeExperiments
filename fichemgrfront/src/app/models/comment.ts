export class Comment {

    aboutAuthor : string;
    aboutGenre : string;
    aboutCadre : string;
    aboutCharacters : string;
    resume : string;
    extrait : string;
    appreciation : string;

    constructor(values: Object = {}) {
        Object.assign(this, values);
    }

}
