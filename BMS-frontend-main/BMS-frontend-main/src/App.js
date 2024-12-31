import { useState } from "react";
import Footer from "./components/Footer/Footer"
import Sidebar from "./scenes/global/Sidebar";
import Dashboard from "./scenes/Dashboard/index.jsx";
import LoginPage from "./components/LoginPage/LoginPage";
import Header from "./components/Header/Header";
import Livemonitoring from "./scenes/Livemonitoring/livemonitoring.jsx";
import Historical from "./scenes/Analytics/Historical/index.jsx";
import Alarms from "./scenes/Analytics/Alarms/index.jsx";
import Daywise from "./scenes/Analytics/Daywise/index.jsx";
import Monthly from "./scenes/Analytics/Monthly/index.jsx";
import SiteDetails from "./scenes/Preferences/SiteDetails/index.jsx"
 import VendorInfo  from "./scenes/Preferences/VendorInfo/index.jsx";
import IssueTracking from "./scenes/Issuetracking/index.jsx";
// import Events from "./scenes/Events/index.jsx";
import { CssBaseline, ThemeProvider } from "@mui/material";
import { ColorModeContext, useMode } from "./theme";
import Users from "./scenes/Users/index.jsx";
import { createBrowserRouter, RouterProvider, Outlet } from "react-router-dom";


const AuthenticatedLayout = ({ isSidebar, setIsSidebar }) => {
  return (
    <div className="app">
      <Sidebar isSidebar={isSidebar} />
      <main className="content">
        <Header />
        <Outlet /> {/* Renders the nested routes */}
      </main>
    </div>
  );
};

function App() {
  const [theme, colorMode] = useMode();
  const [isSidebar, setIsSidebar] = useState(true);
  const [isAuthenticated, setIsAuthenticated] = useState(true);

  const handleLogin = () => {
    // Simulate login success
    setIsAuthenticated(true);
  };

  // Define the router
  const router = createBrowserRouter([
    {
      path: "/login",
      element: <LoginPage onLogin={handleLogin} />,
    },
    {
      path: "/",
      element: isAuthenticated ? (
        <AuthenticatedLayout isSidebar={isSidebar} setIsSidebar={setIsSidebar} />
      ) : (
        <LoginPage onLogin={handleLogin} />
      ),
      children: [
        { path: "/", element: <Dashboard /> },
        { path: "/livemonitoring", element: <Livemonitoring />, },
        { path: "/historical", element: <Historical /> },
        { path: "/alarms", element: <Alarms /> },
        { path: "/daywise", element: <Daywise /> },
        { path: "/monthly", element: <Monthly /> },
        { path: "/siteDetails", element: <SiteDetails /> },
        { path: "/vendorInfo", element: <VendorInfo /> },
        { path: "/issuetracking", element: <IssueTracking /> },
        // { path: "/events", element: <Events /> },
        { path: "/users", element: <Users /> },
      ],
    },
  ]);

  return (
    <ColorModeContext.Provider value={colorMode}>
      <ThemeProvider theme={theme}>
        <CssBaseline />
        <RouterProvider router={router} />
      </ThemeProvider>
    </ColorModeContext.Provider>
  );
}

export default App;