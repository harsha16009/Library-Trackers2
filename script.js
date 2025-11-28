document.getElementById("username-display").innerText =
"Welcome, " + localStorage.getItem("user");

// ---- DSA Structures ----
class HashMap { constructor(){this.map={}} set(k,v){this.map[k]=v;} get(k){return this.map[k];}}
class Queue { constructor(){this.items=[]} enqueue(x){this.items.push(x);} dequeue(){return this.items.shift();}}
class Stack { constructor(){this.items=[]} push(x){this.items.push(x);} }
class Node { constructor(v){this.value=v;this.next=null;}}

class LinkedList {
    constructor(){this.head=null;}
    add(v){
        let n=new Node(v);
        if(!this.head) this.head=n;
        else {let t=this.head; while(t.next) t=t.next; t.next=n;}
    }
}

let bookMap = new HashMap();
let borrowedList = new LinkedList();
let requestQueue = new Queue();
let actionStack = new Stack();
let user = localStorage.getItem("user");

// ---- Add Book Modal ----
function showAddBook(){document.getElementById("addBookPopup").style.display="block";}
function closePopup(){document.getElementById("addBookPopup").style.display="none";}

// ---- Add Book ----
function addBook(){
    let title=document.getElementById("btitle").value;
    let author=document.getElementById("bauthor").value;

    let id="book_" + Date.now();
    bookMap.set(id, {title, author, status:"Available", user:null});

    alert("Book Added!");
    closePopup();
    displayBooks();
}

// ---- Display Books ----
function displayBooks(){
    let container=document.getElementById("book-list");
    container.innerHTML="";

    for(let id in bookMap.map){
        let b=bookMap.get(id);

        container.innerHTML += `
        <div class="book">
            <h2>${b.title}</h2>
            <p>${b.author}</p>
            <p>${b.status}</p>
            <button onclick="borrowBook('${id}')">Borrow</button>
            <button onclick="returnBook('${id}')">Return</button>
        </div>`;
    }
}
displayBooks();

// ---- Borrow Book ----
function borrowBook(id){
    let b=bookMap.get(id);
    if(b.status!=="Available") return alert("Not available!");

    b.status="Borrowed";
    b.user=user;
    borrowedList.add(b.title);
    actionStack.push("Borrowed: "+b.title);
    displayBooks();
}

// ---- Return Book ----
function returnBook(id){
    let b=bookMap.get(id);
    b.status="Available";
    b.user=null;
    actionStack.push("Returned: "+b.title);
    displayBooks();
}

// ---- Queue & History ----
function showHistory(){ alert(actionStack.items.join("\n")); }
function showQueue(){ alert(requestQueue.items.join("\n")); }

// ---- Logout ----
function logout(){
    localStorage.clear();
    window.location.href="index.html";
}
