/* -*- Mode: rjsx -*- */ 
import React from "react";
import ReactDOM from "react-dom";
import 'regenerator-runtime/runtime';
import { get } from 'axios';
import { useState, useEffect } from 'react';

async function fetchStuff(url, setFn) {
  try {
    const a = await get(url);
    setFn(a.data);
  } catch (e) {
    console.error(e);
  }
}

function useStuff({ url, initialState }) {
  const [stuffList, setStuffList] = useState(initialState);
  
  useEffect(() => {
    fetchStuff(url, setStuffList);
  }, []);

  return [stuffList, setStuffList];
}

function HelloMessage(props) {

  const [stuffList, setStuffList] = useStuff(
    {
      url: "/api/stuff",
      initialState: []
    }
  );
  
  return (
    <div>
      <div className="container">
        <h1>Hello {props.name}</h1>
        { stuffList.map(({id, name}) => <div key={id}>{name}</div>) }
      </div>
    </div>
  );
}


let App = document.getElementById("app");

ReactDOM.render(<HelloMessage name="Yomupo" />, App);

