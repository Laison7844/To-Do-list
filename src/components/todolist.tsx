import { useState, useEffect } from "react";
import { deleteToDo, getAllList,completed,inCompleted} from "../service/todoService";
import {  Link, useHistory } from 'react-router-dom';
import { Button, Container, Table } from "react-bootstrap";


type todo={
    id:number,
    title:string,
    description:string,
    completed:boolean;
}
    
const ListToDo: React.FC = () => {
    const navigator = useHistory();

    const [todos, setTodos] = useState<todo[]>([]);

    function listOfToDos(){
        getAllList().then((response) => {
            console.log(response);
            setTodos(response.data);
        }).catch(error => {
            console.error(error);
        })
    }

    function updateTodo(id:number){
        navigator.push(`/edit-todo/${id}`)
    }


    function removeTodo(id:number){
        deleteToDo(id).then((response) => {
            console.log(response.data);
            listOfToDos();
        }).catch(error => {
            console.error(error);
        })
    }
    function completeToDo(id:number){
        completed(id).then(()=>{
            listOfToDos();
        }).catch(error=>{
            console.error(error);
        })
    }
    
    function inCompleteToDo(id:number){
        inCompleted(id).then(()=>{
            listOfToDos();
        }).catch(error=>{
            console.error(error);
        })
    }
    

    useEffect( () => {
        listOfToDos()
     }, [])

    return (<>
       <Container> 
        <h2 style={{textAlign:"center"}}>List of Todos</h2>
        <Link to='/add-todo' className='btn btn-primary mb-2'>Add new</Link>
        <Table>
            <thead>
                <tr>
                    <th>Title</th>
                    <th>Description</th>
                    <th>Complete</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                {
                    todos.map( toDo =>      
                            <tr key={toDo.id}>                         
                             
                                <td> {toDo.title} </td>
                                <td> {toDo.description} </td>
                                <td> {toDo.completed ? "yes" :"no"} </td>
                                <td>
                                    <Button variant="primary" onClick={() => updateTodo(toDo.id)}>Update</Button>
                                    <Button variant="danger" onClick={() => removeTodo(toDo.id)} 
                                        style={{marginLeft: "10px"}}
                                    >Delete</Button>
                                     <Button variant="success" onClick={() => completeToDo(toDo.id)}
                                        style={{marginLeft: "10px"}}
                                    >Complete</Button>
                                     <Button variant="primary" onClick={() => inCompleteToDo(toDo.id)}
                                        style={{marginLeft: "10px"}}
                                    >in Complete</Button>
                                </td>
                            </tr>
                        )
                }
            </tbody>
            </Table>
            </Container>
    </>);
}
export default ListToDo;