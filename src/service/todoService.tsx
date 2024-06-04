import axios from "axios";

const url='http://localhost:8908/api/todolist'
export const getAllList=()=>axios.get(url);
export const addToDo=(todo:any)=>axios.post(url,todo);
export const getToDoById=(id:number)=>axios.get(url+'/'+id);
export const deleteToDo=(id:number)=>axios.delete(url+'/'+id);
export const updateTodo=(id:number,todo:any)=>axios.put(url+'/'+id,todo);
export const completed=(id:number)=>axios.get(url+'/complete/'+id);
export const inCompleted=(id:number)=>axios.get(url+'/inComplete/'+id);

