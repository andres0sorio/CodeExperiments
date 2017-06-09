import { Fiche } from "../models/fiche";
import { Book } from "../models/book";

export let MOCKFICHES = [
  new Fiche({
    id: 1, book: {
      title: "Book One",
      subTitle: "A book about something",
      author: "Andrew McNabb",
      yearPub: 2003,
      editor: "The Mancunian",
      collection: "Greatest Books",
      pages: 200,
      language: "English"
    }
  }),
  new Fiche({
    id: 2, book: {
      title: "Book Two",
      subTitle: "A book about something else",
      author: "Wolfgang Gradl",
      yearPub: 2005,
      editor: "Publishing Editors",
      collection: "History of Computing",
      pages: 200,
      language: "German"
    }
  })
];


/*
let FICHES = [
  new Fiche(
    1, new Book("Book One",
      "A book about something",
      "Andrew McNabb",
      2003,
      "The Mancunian",
      "Greatest Books",
      200,
      "English"))
];
*/