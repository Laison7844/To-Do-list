import React from 'react';
import { Container } from 'react-bootstrap';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import toDoList from './components/todolist';
import todo from './components/todo';
function App() {
  return (
        <Switch>      
          <Route exact path="/" component={toDoList} />
          <Route path='/add-todo' component={todo}></Route>
          <Route path='/edit-todo/:id' component={todo}></Route>
        </Switch>
  );
}

export default App;
