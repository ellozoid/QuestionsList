import React, { useEffect, useState } from 'react';
import Card from './Card/Card';

function App() {
  const perPage = 3;
  const questionsService = 'se';

  const [onPage, setOnPage] = useState(0)
  const [page, setPage] = useState(1);
  const [hasMore, setHasMore] = useState(false);
  const [questionList, setQuestionsList] = useState([]);
  const [loading, setLoading] = useState(false);
  const [searchQuery, setSearchQuery] = useState('');
  let textInput = React.createRef();
  const getQuestionList = () => {
    setLoading(true);
    fetch(`http://localhost:8080/PianoTask/api/questions/search?questionService=${questionsService}&query=${searchQuery}&page=${page}&pageSize=${perPage}`)
      .then(res => res.json())
      .then(res => {
        setHasMore(res.hasMore);
        setQuestionsList([...questionList, ...res.questions]);
        setLoading(false);
        setOnPage(onPage + perPage);
      });
  }

  const handleClick = () => {
    setSearchQuery(textInput.current.value);
    if (textInput.current.value !== searchQuery ||
      textInput.current.value.length === 0) {
      setQuestionsList([]);
      setPage(1);
      setOnPage(0);
      setHasMore(false);
    }
  }

  useEffect(() => {
    if (searchQuery.length !== 0) {
      getQuestionList();
    }
  }, [page, searchQuery]);

  return (
    <div className="container d-flex justify-content-center">
      <div className="card mt-5 p-4">
        <div className="input-group mb-3">
          <input ref={textInput} type="text" className="form-control" />
          <div className="input-group-append">
            <button onClick={handleClick} className="btn btn-primary">
              <i className="fas fa-search"></i>
            </button>
          </div>
        </div>
        <span className="text mb-4">{onPage} questions on page</span>
        {questionList.map((x, i) => {
          return (<Card key={i} questionItem={x}></Card>)
        })}
        <div className="clearfix"></div>
        {hasMore && <button className="btn-load-more" onClick={() => setPage(page + 1)}>{loading ? 'Loading...' : 'Load More'}</button>}
      </div>
    </div>
  );
}

export default App;