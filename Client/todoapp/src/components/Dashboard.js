import React, { useEffect, useState } from "react";
import axios from "axios";

function Dashboard() {
  const [tasks, setTasks] = useState([]);
  const [newTask, setNewTask] = useState({
    description: "",
    dueDate: "",
    isCompleted: false,
  });
  const [userId, setUserId] = useState(null);

  useEffect(() => {
    // Fetch User ID on Component Mount
    fetchUserId();
  }, []);

  useEffect(() => {
    // Fetch Tasks Only After User ID is Set
    if (userId) {
      fetchTasks();
    }
  }, [userId]); // Dependency on userId

  const fetchUserId = async () => {
    try {
      const token = localStorage.getItem("token");
      console.log("aaa", token);
      if (!token) {
        console.error("aaa No token found");
        return;
      }
      const config = {
        method: "get",
        url: "/users/me",
        headers: {
          Authorization: `Bearer ${token}`,
        },
      };
      const response = await axios.request(config);

      setUserId(response.data.id);

      console.log("aaa", response.data);
    } catch (error) {
      console.error("aaa Error fetching user ID", error);
    }
  };

  const fetchTasks = async () => {
    try {
      const token = localStorage.getItem("token");
      const response = await axios.get(`/tasks/get?userId=${userId}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      setTasks(response.data);
    } catch (error) {
      console.error("Error fetching tasks", error);
    }
  };

  const handleTaskChange = (e) => {
    setNewTask({ ...newTask, [e.target.name]: e.target.value });
  };

  const addTask = async () => {
    try {
      const token = localStorage.getItem("token");
      if (!userId) {
        console.error("User ID not available");
        return;
      }
      await axios.post(`/tasks/add?userId=${userId}`, newTask, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      fetchTasks(); // Refresh the task list
    } catch (error) {
      console.error("Error adding task", error);
    }
  };

  return (
    <div>
      <h2>Dashboard</h2>
      <div>
        <h3>Tasks</h3>
        <ul>
          {tasks.map((task) => (
            <li key={task.id}>{task.description}</li>
          ))}
        </ul>
        <h3>Add Task</h3>
        <input
          type="text"
          name="description"
          placeholder="Description"
          value={newTask.description}
          onChange={handleTaskChange}
        />
        <input
          type="date"
          name="dueDate"
          value={newTask.dueDate}
          onChange={handleTaskChange}
        />
        <button onClick={addTask}>Add Task</button>
      </div>
    </div>
  );
}

export default Dashboard;
