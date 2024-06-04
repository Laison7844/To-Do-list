import { FormEvent, MouseEvent, useEffect, useState } from "react";
import { Route, useParams,useHistory } from "react-router-dom";
import{getToDoById,addToDo,updateTodo, completed,inCompleted} from "../service/todoService"


interface RouteParams{
    id :string
}

const ToDo:React.FC=()=>{
    const[title,setTitle]=useState<string>("")
    const[description,setDescription]=useState<string>("")
    const[complete,setComplete]=useState<boolean>(false)
  
   const navigator = useHistory();
   const {id} = useParams<RouteParams>();

const [errors, setErrors] = useState({ title: ""});

const validateForm = () => {
  let valid = true;
  const errorsCopy = { ...errors };
  if (title.trim()) {
    errorsCopy.title = "";
  } else {
    errorsCopy.title = "Title is Required";
    valid = false;
  }
  setErrors(errorsCopy);
  return valid;
};


useEffect(() => {
    getToDoById(Number(id)).then((response) => {
        setTitle(response.data.title);
      setDescription(response.data.description);
      setComplete(response.data.complete);
    }).catch(error => {
      console.error(error);
    })

  }, [id])
  

function saveOrUpdateToDo(e:FormEvent){
    e.preventDefault();
      
    if (validateForm()) {
    const todo = { title, description,complete}

    if(id){
        updateTodo(Number(id), todo).then((response) => {
        console.log(response.data);
        navigator.push(`/`);
      }).catch(error => {
        console.error(error);
      })
    }else {
      addToDo(todo).then((response) => {
        console.log(response.data);
        navigator.push('/')
      }).catch(error => {
        console.error(error);
      })
    }
  }
  };
  
  function pageTitle(){
    if(id){
        return <h2 className='text-center'>Update ToDOList</h2>
    } else {
        return <h2 className='text-center'>Add NeW ToDo</h2>
    }
  }

  return (
    <div className='container'><br /><br />
      <div className='row'>
          <div className='card col-md-6 offset-md-3 offset-md-3'>
              {
                pageTitle()
              }

              <div className='card-body'>
                  <form>
                      <div className='form-group mb-2'>
                          <label className='form-label'>Title Name:</label>
                          <input
                            type='text'
                            name='title'
                            placeholder='Enter todo title Name'
                            className='form-control'
                            value={title}
                            onChange={(e) => setTitle(e.target.value)}

                          >
                          </input>
                      </div>

                      <div className='form-group mb-2'>
                          <label className='form-label'>ToDo Description:</label>
                          <input
                            type='text'
                            name='description'
                            placeholder='Enter ToDo Description'
                            value={description}
                            onChange={(e) => setDescription(e.target.value)}
                            className='form-control'
                          >
                          </input>
                      </div>
                      <button type="button" className="btn btn-primary" onClick={(e) => saveOrUpdateToDo(e)}>
                        Submit
                       </button>
                  </form> 
              </div>
          </div>

      </div>

    </div>
   
  )
            }
export default ToDo;
