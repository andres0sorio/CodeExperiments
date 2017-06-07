export class Book {

    title: string;
    subTitle: string;
    author: string;
    yearPub: number;
    editor: string;
    collection: string;
    pages: number;
    language: string;

    constructor(values: Object = {}) {
        Object.assign(this, values);
    }
    
}
