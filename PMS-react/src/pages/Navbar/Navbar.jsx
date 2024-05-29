import { Dialog, DialogHeader, DialogTrigger,DialogContent } from "@/components/ui/dialog";
import { Button } from "@/components/ui/button";
import CreateProjectForm from "../Project/CreateProjectForm";
import { DropdownMenu, DropdownMenuItem, DropdownMenuTrigger,DropdownMenuContent } from "@/components/ui/dropdown-menu";
import { PersonIcon } from "@radix-ui/react-icons";
import { useNavigate } from "react-router-dom";

const Navbar = () => {
  const navigate = useNavigate();
  return (
    <div className=" border-b py-4 px-5 flex items-center justify-between">
      <div className="flex items-center gap-8">
        <p onClick={()=>navigate("/")} className="cursor-pointer">Project Management</p>
        <Dialog>
          <DialogTrigger>
            <Button variant="ghost">New project</Button>
          </DialogTrigger>
          <DialogContent>
          <DialogHeader>Create New Project</DialogHeader>
            <CreateProjectForm />
          </DialogContent>
        </Dialog>
        <Button onClick={()=>navigate("/upgrade_plan")} variant="ghost">Upgrade</Button>
      </div>
      <div className="flex gap-3 items-center">
        <DropdownMenu>
            <DropdownMenuTrigger>
                <Button variant="outline" size='icon' className='rounded-full border-2 border-gray-500'>
                    <PersonIcon/>
                </Button>
            </DropdownMenuTrigger>
            <DropdownMenuContent>
                <DropdownMenuItem>Logout</DropdownMenuItem>
            </DropdownMenuContent>
        </DropdownMenu>
        <p>PahasaraJ</p>
      </div>
    </div>
  );
};

export default Navbar;