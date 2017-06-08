export class Comment {

    aboutAuthor : string;
    aboutGenre : string;
    aboutContext : string;
    aboutCharacters : string;
    resume : string;
    extraits : string;
    appreciation : string;

    constructor(values: Object = {}) {
        Object.assign(this, values);
    }

}
